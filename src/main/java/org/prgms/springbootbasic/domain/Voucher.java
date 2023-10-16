package org.prgms.springbootbasic.domain;

import java.util.UUID;

public interface Voucher {
    UUID getVoucherId();
    long discount(long beforeDiscount);
    long getDiscountAmount();
}
