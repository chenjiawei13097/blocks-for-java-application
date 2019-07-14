package org.firefly.provider.springboot.service;

import org.firefly.provider.springboot.domain.dto.BillDTO;
import org.firefly.provider.springboot.domain.dto.BillQueryDTO;
import org.firefly.provider.springboot.domain.service.BillService;
import org.firefly.provider.springboot.domain.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Override
    public BillDTO queryById(Long id) {
        return new BillDTO(id, "Wuzhou Hotel", "Xiaoli Wong", 45.00, "红烧牛肉米线",
                DateTimeUtil.parse("2019-07-14 12:01:56"), "Finished",
                DateTimeUtil.parse("2019-07-14 12:45:56"), DateTimeUtil.parse("2019-07-14 12:45:56"));
    }

    @Override
    public BillQueryDTO queryByHotel(String hotel) {
        List<BillDTO> billDTOList = new ArrayList<>();

        billDTOList.add(new BillDTO(1L, hotel, "Xiaoli Wong", 45.00, "红烧牛肉米线",
                DateTimeUtil.parse("2019-07-14 12:01:56"), "Finished",
                DateTimeUtil.parse("2019-07-14 12:45:56"), DateTimeUtil.parse("2019-07-14 12:45:56")));
        billDTOList.add(new BillDTO(2L, hotel, "Xiaoli Wong", 45.00, "红烧牛肉米线",
                DateTimeUtil.parse("2019-07-15 12:01:56"), "Finished",
                DateTimeUtil.parse("2019-07-15 12:45:56"), DateTimeUtil.parse("2019-07-15 12:45:56")));

        return new BillQueryDTO(billDTOList, 425);
    }
}
