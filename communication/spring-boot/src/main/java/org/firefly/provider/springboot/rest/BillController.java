package org.firefly.provider.springboot.rest;

import org.firefly.provider.springboot.domain.dto.BillDTO;
import org.firefly.provider.springboot.domain.dto.BillQueryDTO;
import org.firefly.provider.springboot.domain.service.BillService;
import org.firefly.provider.springboot.rest.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/queryById/{id}")
    public Response<BillDTO> queryById(@PathVariable Long id) {
        return Response.success(billService.queryById(id));
    }

    @GetMapping("/queryByHotel/{hotel}")
    public Response<BillQueryDTO> queryByHotel(@PathVariable String hotel) {
        return Response.success(billService.queryByHotel(hotel));
    }
}
