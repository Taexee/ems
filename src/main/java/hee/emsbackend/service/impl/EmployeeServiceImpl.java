package hee.emsbackend.service.impl;

import hee.emsbackend.dto.EmployeeDto;
import hee.emsbackend.entity.Employee;
import hee.emsbackend.exception.ResourceNotFoundException;
import hee.emsbackend.mapper.EmployeeMapper;
import hee.emsbackend.repository.EmployeeRepository;
import hee.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("일치하는 사원 없음(사원번호 : " + employeeId + ")"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
