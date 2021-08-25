package br.com.alelo.consumer.consumerpat.gateway.jpa.adpter;

import br.com.alelo.consumer.consumerpat.core.entity.Extract;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ExtractJpa;

public final class ExtractJpaAdapter {
    private ExtractJpaAdapter() {
    }

    public static Extract toExtract(ExtractJpa entity) {
        return ObjectUtils.copyTo(entity, Extract.class);
    }

    public static ExtractJpa fromExtract(Extract extract) {
        return ObjectUtils.copyTo(extract, ExtractJpa.class);
    }
}
