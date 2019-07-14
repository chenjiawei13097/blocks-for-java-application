package org.firefly.provider.springboot.domain.service;

import org.firefly.provider.springboot.domain.dto.BillDTO;
import org.firefly.provider.springboot.domain.dto.BillQueryDTO;

public interface BillService {
    BillDTO queryById(Long id);

    BillQueryDTO queryByHotel(String hotel);
}
