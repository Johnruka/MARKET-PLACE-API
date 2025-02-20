package se.lexicon.marketplaceapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(length = 8000)
    private String content;

    @Lob
    private List<String> attachments;

    private LocalDateTime createDateTime;

    private LocalDateTime expiryDateTime;

    private Integer type;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    @PrePersist
    public void initialData() {
        createDateTime = LocalDateTime.now();
        expiryDateTime = createDateTime.plusDays(30);
    }
}
