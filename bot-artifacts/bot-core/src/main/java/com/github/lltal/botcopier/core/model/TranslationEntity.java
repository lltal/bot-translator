package com.github.lltal.botcopier.core.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(
        name = "translation-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("consumers"),
                @NamedAttributeNode("suppliers")
        }
)
@Entity
@Table(name = "translation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Data
public class TranslationEntity{
    @Id
    @Column(name = "user_id")
    @NonNull
    private Long userId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "consumers_translations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "consumer_id")
    )
    private List<ConsumerEntity> consumers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "suppliers_translations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private List<SupplierEntity> suppliers;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "key_words",
            joinColumns = @JoinColumn(name = "words_id"))
    @Column(name = "word", nullable = false)
    @NonNull
    private Set<String> keyWords;
}