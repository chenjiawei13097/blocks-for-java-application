package org.firefly.provider.springboot.domain.dto;

import java.util.List;

public class BillQueryDTO {
    private List<BillDTO> currentBills;
    private int totalNumber;

    public BillQueryDTO(List<BillDTO> currentBills, int totalNumber) {
        this.currentBills = currentBills;
        this.totalNumber = totalNumber;
    }

    public List<BillDTO> getCurrentBills() {
        return currentBills;
    }

    public void setCurrentBills(List<BillDTO> currentBills) {
        this.currentBills = currentBills;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
}
