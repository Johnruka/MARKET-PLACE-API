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
    @Column(length = 65000)
    private String content;

    @Lob
    private List<String> attachments;
    private LocalDateTime dateTime;
    private Integer type; // 1 registration, 2 reset password
    //Add more fields as needed

    @PrePersist
    public void initialData() {
        dateTime = LocalDateTime.now();
    }
}
