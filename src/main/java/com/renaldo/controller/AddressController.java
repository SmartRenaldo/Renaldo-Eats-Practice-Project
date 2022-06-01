package com.renaldo.controller;

import com.renaldo.common.R;
import com.renaldo.pojo.Address;
import com.renaldo.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public R<Address> save(@RequestBody Address address) {
        return R.success(addressService.save(address));
    }

    @PutMapping
    public R<String> update(@RequestBody Address address) {
        Boolean update = addressService.update(address);

        if (update) {
            return R.success("Update successfully!");
        }

        return R.error("Update failed!");
    }

    @PutMapping("/default")
    public R<Address> setDefault(@RequestBody Address address) {
        return R.success(addressService.setDefault(address));
    }

    /**
     * query by id
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        Optional<Address> byId = addressService.findById(id);

        if (byId.isPresent()) {
            return R.success(byId.get());
        } else {
            return R.error("No such address");
        }
    }

    /**
     * query default address
     */
    @GetMapping("/default")
    public R<Address> getDefault() {
        Optional<Address> addressOptional = addressService.findDefault();

        return addressOptional.map(R::success).orElseGet(() -> R.error("No default address"));
    }

    /**
     * query all address, order by isDefault desc (priority), and order by dateModified
     */
    @GetMapping("/list")
    public R<List<Address>> list(Address address) {
        return R.success(addressService.list(address));
    }
}
