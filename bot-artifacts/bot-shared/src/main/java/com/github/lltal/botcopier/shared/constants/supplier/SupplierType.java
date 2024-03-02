package com.github.lltal.botcopier.shared.constants.supplier;

import com.github.lltal.converter.shared.annotation.Convertee;
import com.github.lltal.converter.shared.annotation.ConverteeField;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierBeanNames;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierTypeStringView;

@Convertee(converterBeanName = SupplierBeanNames.CONVERTER)
public enum SupplierType {
    @ConverteeField(stringView = SupplierTypeStringView.SUPERGROUP)
    SUPERGROUP,
    @ConverteeField(stringView = SupplierTypeStringView.CHANNEL)
    CHANNEL
}
