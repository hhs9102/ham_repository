package me.ham.sample;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hateoas")
    public Resource<Hateoas> hateoas(){
        Hateoas hateoas =new Hateoas();
        hateoas.setName("hey,");
        hateoas.setPrifix("hosik");

        Resource<Hateoas> hateoasResource = new Resource<>(hateoas);
        hateoasResource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(SampleController.class).hateoas()).withSelfRel());
        return hateoasResource ;
    }
}
