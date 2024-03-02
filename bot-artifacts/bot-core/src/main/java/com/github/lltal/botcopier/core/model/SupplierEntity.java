package com.github.lltal.botcopier.core.model;

import com.github.lltal.botcopier.shared.constants.supplier.SupplierType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "message_supplier")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class SupplierEntity {
    @Id
    @Column(name = "supplier_id")
    private Long supplierId;

    @Enumerated(value = EnumType.STRING)
    private SupplierType type;

    @Column(name = "supplier_name")
    private String supplierName;

    @ManyToMany(mappedBy = "suppliers")
    private List<TranslationEntity> translations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierEntity that = (SupplierEntity) o;
        return Objects.equals(supplierId, that.supplierId) && type == that.type && Objects.equals(supplierName, that.supplierName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, type, supplierName);
    }
}
