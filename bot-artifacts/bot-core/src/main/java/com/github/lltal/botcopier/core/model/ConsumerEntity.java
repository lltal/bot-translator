package com.github.lltal.botcopier.core.model;

import com.github.lltal.botcopier.shared.constants.consumer.ConsumerType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "message_consumer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class ConsumerEntity {
    @Id
    @Column(name = "consumer_id")
    private Long consumerId;

    @Enumerated(value = EnumType.STRING)
    private ConsumerType type;

    @Column(name = "consumer_name")
    private String consumerName;

    @ManyToMany(mappedBy = "consumers")
    private List<TranslationEntity> translations;
}
