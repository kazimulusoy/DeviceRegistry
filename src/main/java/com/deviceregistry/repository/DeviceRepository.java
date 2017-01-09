package com.deviceregistry.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.deviceregistry.domain.Device;

/**
 * The Interface DeviceRepository.
 */
@Transactional
public interface DeviceRepository extends CrudRepository<Device, Long>{
	
	/**
	 * Find by serial number.
	 *
	 * @param serialNumber the serial number
	 * @return the list
	 */
	List<Device> findBySerialNumber(String serialNumber);
	
	/**
	 * Delete devices by serial number.
	 *
	 * @param serialNumber the serial number
	 */
	@Modifying
    @Transactional
    @Query("delete from Device d where d.serialNumber = ?1")
    void deleteDevicesBySerialNumber(String serialNumber);
	
}
