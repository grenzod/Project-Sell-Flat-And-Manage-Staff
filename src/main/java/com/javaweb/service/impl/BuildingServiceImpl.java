package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.UpgradeOrAddBuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);

        List<BuildingSearchResponse> resultList = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse buildingDTO = buildingDTOConverter.toBuildingDTO(item);
            resultList.add(buildingDTO);
        }
        return resultList;
    }

    @Override
    public ResponseDTO listStaffs(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = building.getUsers();

        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(UserEntity x : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(x.getFullName());
            staffResponseDTO.setStaffId(x.getId());
            if(staffAssignment.contains(x)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        ResponseDTO kq = new ResponseDTO();
        kq.setData(staffResponseDTOS);
        kq.setMessage("Success");
        return kq;
    }

    private void toSolve(String[] s, BuildingEntity buildingEntity){
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for(String x : s){
            RentAreaEntity r = new RentAreaEntity();
            r.setBuilding(buildingEntity);
            r.setValue(Long.parseLong(x));
            rentAreaEntities.add(r);
        }
        buildingEntity.setItems(rentAreaEntities);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void UpgradeOrAddBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = UpgradeOrAddBuildingConverter.toUpgradeOrAddBuildingConverter(buildingDTO);
        String[] s = buildingDTO.getRentArea().split(",");
        saveThumbnail(buildingDTO, buildingEntity);

        toSolve(s, buildingEntity);
    }

    @Override
    public void GiveBuildingForStaff(AssignmentDTO assignmentDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentDTO.getId()).get();
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentDTO.getStaffs());
        buildingEntity.setUsers(staffs);
    }

    @Override
    public void DeleteBulding(Long[] ids) {
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public List<BuildingSearchResponse> getAllBuilding(Pageable pageable, BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuilding(pageable, buildingSearchBuilder);

        List<BuildingSearchResponse> resultList = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse buildingDTO = buildingDTOConverter.toBuildingDTO(item);
            resultList.add(buildingDTO);
        }
        return resultList;
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        return buildingRepository.countTotalItem(buildingSearchBuilder);
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office/building/" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }
}
