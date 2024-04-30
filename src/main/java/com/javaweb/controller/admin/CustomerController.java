package com.javaweb.controller.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private CustomerService customerService;

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
}
