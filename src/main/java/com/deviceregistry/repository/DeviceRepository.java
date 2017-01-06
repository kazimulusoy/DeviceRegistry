package com.deviceregistry.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.deviceregistry.domain.Device;

@Transactional
public interface DeviceRepository extends CrudRepository<Device, Long>{
	
	List<Device> findBySerialNumber(String serialNumber);
	
}
