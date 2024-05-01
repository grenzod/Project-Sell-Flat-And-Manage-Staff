package com.javaweb.controller.admin;
import com.javaweb.converter.BuildingUpgradeConverter;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private BuildingService buildingService;

    //Tìm kiếm tòa nhà
    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        mav.addObject("listStaffs", iUserService.getStaffs());
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());

        DisplayTagUtils.of(request, buildingSearchRequest);
        List<BuildingSearchResponse> list;
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
            list = buildingService.getAllBuilding(PageRequest.of(buildingSearchRequest.getPage() - 1,
                    buildingSearchRequest.getMaxPageItems()), buildingSearchRequest);
        }
        else{
            list = buildingService.getAllBuilding(PageRequest.of(buildingSearchRequest.getPage() - 1,
                    buildingSearchRequest.getMaxPageItems()), buildingSearchRequest);
        }
        buildingSearchRequest.setListResult(list);
        buildingSearchRequest.setTotalItems(buildingService.countTotalItems(buildingSearchRequest));
//        mav.addObject("buildingList", list);
        return mav;
    }

    //Thêm tòa nhà
    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute BuildingDTO buildingDTO ,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }

    //Sửa tòa nhà
    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");

        //Xuống db lấy dữ liệu theo id
        BuildingDTO buildingDTO = BuildingUpgradeConverter.toBuildingUpgrade(id);

        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        return mav;
    }
}
