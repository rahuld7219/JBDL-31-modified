package com.example.L10springjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private BranchRepo branchRepo;


//    public Employee createEmployee(EmployeeRequestDTO employeeDTO){
//        Employee employee = new Employee();
//        employee.setName(employeeDTO.getName());
//       employeeRepo.save(employee);
//       return employee;
//    }


    @Transactional(rollbackOn = {EmployeeAppException.class}) // everything inside this method acts as a single transaction
    // With @Transactional annotation jpa will create a method(proxy method) which will wrap the below method,
    // that will do start transaction and commit or rollback based on the exception thrown by the below method
    public Employee createEmployee(Employee employee) throws EmployeeAppException {
        Integer value =10;
        Branch branch = branchRepo.findById(1).get();
        employee.setBranch(branch);
        addressRepo.save(employee.getAddress());
        // save() method internally also have @Transactional annotation in EntityManager
        // but here it will not be saved/commited here even if save successully executed,
        // because of the @Transactional over our createEmployee() method that acts as a bigger transaction.
        // i.e. following the atomic property, i.e. for a transaction either all operation will be successfull or niether will be.
        // So commit/rollback of a transaction will not work if it is part of/inside another bigger transaction.
        // i.e., The annotation that comes first will be given preference here
        employeeRepo.save(employee);
        System.out.println(value.equals(10));
        if(value.equals(10)){
            throw new EmployeeAppException();
        }
        return employee;
    }

}
