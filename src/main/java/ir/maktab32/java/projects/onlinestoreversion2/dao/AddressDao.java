package ir.maktab32.java.projects.onlinestoreversion2.dao;

import ir.maktab32.java.projects.onlinestoreversion2.model.Address;

import java.util.List;

public interface AddressDao {
    void addAddress(Address address);
    void deleteAddress(int addressId);
    void editAddress(int addressId, Address address);
    Address findAddressById(int addressId);
    List<Address> findAllAddresses();
}
