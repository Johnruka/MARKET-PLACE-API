package se.lexicon.marketplaceapi.domain.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class AdvertisementDTOForm {

    private Long id;

    private String title;

    private String content;

    private List<String> attachments;

    private LocalDate createDateTime;

    private LocalDate expiryDateTime;

    private PersonDTOForm person;
}
