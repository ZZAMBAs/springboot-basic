package org.prgms.springbootbasic.repository.voucher;

import lombok.extern.slf4j.Slf4j;
import org.prgms.springbootbasic.domain.voucher.VoucherPolicy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile({"local"})
@Slf4j
public class VoucherMemoryRepository implements VoucherRepository{
    private final ConcurrentHashMap<UUID, VoucherPolicy> mem = new ConcurrentHashMap<>();

    @Override
    public Optional<VoucherPolicy> findById(UUID voucherId) {
        return Optional.ofNullable(mem.get(voucherId));
    }

    @Override
    public List<VoucherPolicy> findAll() {
        return new ArrayList<>(mem.values());
    }

    @Override
    public VoucherPolicy save(VoucherPolicy voucherPolicy) {
        mem.putIfAbsent(voucherPolicy.getVoucherId(), voucherPolicy);
        return voucherPolicy;
    }

    @Override
    public Optional<VoucherPolicy> deleteById(UUID voucherId) {
        return Optional.ofNullable(mem.remove(voucherId));
    }

    @Override
    public void deleteAll() {
        mem.clear();
    }
}
