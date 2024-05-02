package com.javaweb.controller.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView CustomerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch", customerSearchRequest);
        mav.addObject("listStaffs", iUserService.getStaffs());

        DisplayTagUtils.of(request, customerSearchRequest);
        List<CustomerSearchResponse> list;
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            list = customerService.getAllCustomers(PageRequest.of(customerSearchRequest.getPage() - 1,
                    customerSearchRequest.getMaxPageItems()), customerSearchRequest);
        }
        else{
            list = customerService.getAllCustomers(PageRequest.of(customerSearchRequest.getPage() - 1,
                    customerSearchRequest.getMaxPageItems()), customerSearchRequest);
        }
        customerSearchRequest.setListResult(list);
        customerSearchRequest.setTotalItems(customerService.countTotalItems(customerSearchRequest));
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView CustomerEdit(@ModelAttribute CustomerDTO customerDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit", customerDTO);
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView CustomerEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        CustomerEntity customer = customerRepository.findById(id).get();
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        mav.addObject("customerEdit", customerDTO);
        mav.addObject("transactionType", TransactionType.transactionType());

        // Lấy ra 2 loại hình giao dịch của khác hàng từ data
        List<TransactionEntity> typeCSKH = transactionRepository.findByCodeAndCustomer_Id("CSKH", id);
        List<TransactionEntity> typeDDX = transactionRepository.findByCodeAndCustomer_Id("DDX", id);
        mav.addObject("typeCSKH", typeCSKH);
        mav.addObject("typeDDX", typeDDX);
        return mav;
    }
}
