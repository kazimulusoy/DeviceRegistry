package com.deviceregistry.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Hardware.
 *
 * @author Kazim Ulusoy
 */
@Entity
@DiscriminatorValue(value = "tracking-device")
@Table(name = "device")
public class Device {
	/**
	 * The serialVersionUID is a universal version identifier for a Serializable
	 * class. Deserialization uses this number to ensure that a loaded class
	 * corresponds exactly to a serialized object. If no match is found, then an
	 * InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	/** The description. */
	@Column(name = "description", nullable = false)
	private String description;

	/** The vendor. */
	@Column(name = "vendor", nullable = false)
	private String vendor;

	/** The serial number. */
	@Column(name = "serialNumber", nullable = false, unique = true)
	private String serialNumber;

	/** The hardware version. */
	@Column(name = "hardwareVersion", nullable = false)
	private String hardwareVersion;
	
	/** The software version. */
	@Column(name = "softwareVersion", nullable = false)
	private String softwareVersion;

	/**
	 * Instantiates a new hardware.
	 */
	public Device() {
		super();
	}
	
	/**
	 * Instantiates a new hardware.
	 *
	 * @param description the description
	 * @param vendor the vendor
	 * @param serialNumber the serial number
	 * @param hardwareType the hardware type
	 * @param hardwareVersion the hardware version
	 * @param softwareVersion the software version
	 */
	public Device(String description, String vendor, String serialNumber, String hardwareVersion, String softwareVersion) {
		super();
		this.description = description;
		this.vendor = vendor;
		this.serialNumber = serialNumber;
		this.hardwareVersion = hardwareVersion;
		this.softwareVersion = softwareVersion;
	}

	/**
	 * Gets the vendor.
	 *
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * Sets the vendor.
	 *
	 * @param vendor
	 *            the new vendor
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * Gets the serial number.
	 *
	 * @return the serial number
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Sets the serial number.
	 *
	 * @param serialNumber
	 *            the new serial number
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Gets the hardware version.
	 *
	 * @return the hardware version
	 */
	public String getHardwareVersion() {
		return hardwareVersion;
	}

	/**
	 * Sets the hardware version.
	 *
	 * @param hardwareVersion
	 *            the new hardware version
	 */
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the software version.
	 *
	 * @return the software version
	 */
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * Sets the software version.
	 *
	 * @param softwareVersion the new software version
	 */
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	
}
