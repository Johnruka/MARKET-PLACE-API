package se.lexicon.marketplaceapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.marketplaceapi.Service.AdvertisementService;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOForm;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView;
import se.lexicon.marketplaceapi.domain.dto.UserDTOForm;

@RequestMapping("/api/v1/advertisements")
@RestController
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }


    //Swagger UI annotations
    @Operation(summary = "Create a new advert", description = "Creates a new advert in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "advert created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")

    })
    @PostMapping
    public ResponseEntity<AdvertisementDTOView> doCreate(@RequestBody @Valid UserDTOForm userDTOForm) {
        System.out.println("DTO Form: " + userDTOForm);
        AdvertisementDTOView responseBody = advertisementService.create(new AdvertisementDTOForm());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
}

@GetMapping
public ResponseEntity<AdvertisementDTOView> doDisplayAdvertisements(AdvertisementDTOForm advertisementDTOForm){
    System.out.println("DTO Form: " + advertisementDTOForm);
    AdvertisementDTOView responseBody = advertisementService.display(new AdvertisementDTOForm());
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);

}
}
