package com.rest.webservices.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	
	@GetMapping("v1/person")
	public PersonV1 personv1() {
		return new PersonV1(" Jagan Kaveripakam");
	}
	@GetMapping("v2/person")
public PersonV2 personv2() {
	
	return new PersonV2(new Name("Jagan","Kaveripakam"));
}
	@GetMapping(value="person/param", params="v1")
	public PersonV1 param1() {
		return new PersonV1(" Jagan Kaveripakam");
	}
	@GetMapping(value= "person/param", params="v2")
public PersonV2 param2() {
	
	return new PersonV2(new Name("Jagan","Kaveripakam"));
}
	
	
	@GetMapping(value="person/header", headers="v=1")
	public PersonV1 header1() {
		return new PersonV1(" Jagan Kaveripakam");
	}
	@GetMapping(value= "person/header", headers="v=2")
public PersonV2 header2() {
	
	return new PersonV2(new Name("Jagan","Kaveripakam"));
}
	
	@GetMapping(value="person/produces", produces="application/vnd.comp.app-v1+json")
	public PersonV1 producerv1() {
		return new PersonV1(" Jagan Kaveripakam");
	}
	@GetMapping(value= "person/produces", produces="application/vnd.comp.app-v2+json")
public PersonV2 producerv2() {
	
	return new PersonV2(new Name("Jagan","Kaveripakam"));
}

}
