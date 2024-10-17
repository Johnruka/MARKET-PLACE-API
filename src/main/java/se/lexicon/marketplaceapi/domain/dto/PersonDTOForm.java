package se.lexicon.marketplaceapi.domain.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Builder
public class PersonDTOForm {

    private Long id;

    private String name;
}
