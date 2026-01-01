package com.clinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Patient entity class.
 * 
 * Best Practices Applied:
 * 1. @Nested classes for logical grouping
 * 2. @DisplayName for readable test names
 * 3. @BeforeEach for test setup (SUT creation)
 * 4. @ParameterizedTest for data-driven tests
 * 5. AAA pattern (Arrange-Act-Assert)
 * 6. One assertion concept per test
 * 7. Descriptive test method names
 * 8. Testing edge cases (null, empty, special characters)
 */
@DisplayName("Patient Entity Tests")
class PatientTest {

    // SUT (System Under Test)
    private Patient sut;

    // Test fixtures (constants)
    private static final Long DEFAULT_ID = 1L;
    private static final String DEFAULT_HN = "HN001";
    private static final String DEFAULT_PNAME = "Mr.";
    private static final String DEFAULT_FNAME = "John";
    private static final String DEFAULT_LNAME = "Doe";
    private static final String DEFAULT_NNAME = "Johnny";
    private static final String DEFAULT_SEX = "M";
    private static final String DEFAULT_PERSONALID = "1234567890123";
    private static final String DEFAULT_BIRTHDAY = "1990-01-15";
    private static final String DEFAULT_OCCUPATION = "Engineer";
    private static final String DEFAULT_NATIONALITY = "Thai";
    private static final String DEFAULT_ADDRESS1 = "123 Main Street";
    private static final String DEFAULT_TEL1 = "0812345678";
    private static final String DEFAULT_EMAIL = "john.doe@example.com";
    private static final BigDecimal DEFAULT_SALARY = new BigDecimal("50000.00");
    private static final String DEFAULT_STATUS = "ACTIVE";
    private static final LocalDateTime DEFAULT_DAT = LocalDateTime.of(2024, 1, 15, 10, 30, 0);

    @BeforeEach
    void setUp() {
        sut = new Patient();
    }

    // =========================================================================
    // Constructor Tests
    // =========================================================================

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Default constructor should create Patient with all null fields")
        void defaultConstructor_ShouldCreatePatientWithNullFields() {
            // Assert - checking key fields
            assertAll("All key fields should be null",
                    () -> assertNull(sut.getId()),
                    () -> assertNull(sut.getHn()),
                    () -> assertNull(sut.getFname()),
                    () -> assertNull(sut.getLname()),
                    () -> assertNull(sut.getPersonalid()),
                    () -> assertNull(sut.getEmail()),
                    () -> assertNull(sut.getStatus()));
        }
    }

    // =========================================================================
    // ID Field Tests
    // =========================================================================

    @Nested
    @DisplayName("ID Field Tests")
    class IdTests {

        @Test
        @DisplayName("setId and getId should work correctly")
        void setAndGetId_ShouldWorkCorrectly() {
            // Act
            sut.setId(DEFAULT_ID);

            // Assert
            assertEquals(DEFAULT_ID, sut.getId());
        }

        @ParameterizedTest(name = "ID {0} should be accepted")
        @ValueSource(longs = { 0L, 1L, 100L, Long.MAX_VALUE })
        @DisplayName("setId should accept various Long values")
        void setId_ShouldAcceptVariousValues(Long id) {
            // Act
            sut.setId(id);

            // Assert
            assertEquals(id, sut.getId());
        }

        @Test
        @DisplayName("setId should accept null value")
        void setId_ShouldAcceptNull() {
            // Arrange
            sut.setId(DEFAULT_ID);

            // Act
            sut.setId(null);

            // Assert
            assertNull(sut.getId());
        }
    }

    // =========================================================================
    // HN (Hospital Number) Field Tests
    // =========================================================================

    @Nested
    @DisplayName("HN (Hospital Number) Field Tests")
    class HnTests {

        @Test
        @DisplayName("setHn and getHn should work correctly")
        void setAndGetHn_ShouldWorkCorrectly() {
            // Act
            sut.setHn(DEFAULT_HN);

            // Assert
            assertEquals(DEFAULT_HN, sut.getHn());
        }

        @ParameterizedTest(name = "HN ''{0}'' should be accepted")
        @ValueSource(strings = { "HN001", "HN-12345", "123456", "P001" })
        @DisplayName("setHn should accept various valid HN formats")
        void setHn_ShouldAcceptValidFormats(String hn) {
            // Act
            sut.setHn(hn);

            // Assert
            assertEquals(hn, sut.getHn());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("setHn should accept null and empty values")
        void setHn_ShouldAcceptNullAndEmpty(String hn) {
            // Act
            sut.setHn(hn);

            // Assert
            assertEquals(hn, sut.getHn());
        }
    }

    // =========================================================================
    // Name Fields Tests (pname, fname, lname, nname)
    // =========================================================================

    @Nested
    @DisplayName("Name Fields Tests")
    class NameTests {

        @Test
        @DisplayName("setPname and getPname should work correctly")
        void setAndGetPname_ShouldWorkCorrectly() {
            // Act
            sut.setPname(DEFAULT_PNAME);

            // Assert
            assertEquals(DEFAULT_PNAME, sut.getPname());
        }

        @ParameterizedTest(name = "Prefix ''{0}'' should be accepted")
        @ValueSource(strings = { "Mr.", "Mrs.", "Ms.", "Dr.", "นาย", "นาง", "นางสาว" })
        @DisplayName("setPname should accept various name prefixes")
        void setPname_ShouldAcceptVariousPrefixes(String prefix) {
            // Act
            sut.setPname(prefix);

            // Assert
            assertEquals(prefix, sut.getPname());
        }

        @Test
        @DisplayName("setFname and getFname should work correctly")
        void setAndGetFname_ShouldWorkCorrectly() {
            // Act
            sut.setFname(DEFAULT_FNAME);

            // Assert
            assertEquals(DEFAULT_FNAME, sut.getFname());
        }

        @Test
        @DisplayName("setFname should handle Thai characters")
        void setFname_ShouldHandleThaiCharacters() {
            // Arrange
            String thaiName = "สมชาย";

            // Act
            sut.setFname(thaiName);

            // Assert
            assertEquals(thaiName, sut.getFname());
        }

        @Test
        @DisplayName("setLname and getLname should work correctly")
        void setAndGetLname_ShouldWorkCorrectly() {
            // Act
            sut.setLname(DEFAULT_LNAME);

            // Assert
            assertEquals(DEFAULT_LNAME, sut.getLname());
        }

        @Test
        @DisplayName("setNname and getNname should work correctly")
        void setAndGetNname_ShouldWorkCorrectly() {
            // Act
            sut.setNname(DEFAULT_NNAME);

            // Assert
            assertEquals(DEFAULT_NNAME, sut.getNname());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("setFname should accept null and empty values")
        void setFname_ShouldAcceptNullAndEmpty(String name) {
            // Act
            sut.setFname(name);

            // Assert
            assertEquals(name, sut.getFname());
        }
    }

    // =========================================================================
    // Personal Information Tests
    // =========================================================================

    @Nested
    @DisplayName("Personal Information Tests")
    class PersonalInfoTests {

        @Test
        @DisplayName("setSex and getSex should work correctly")
        void setAndGetSex_ShouldWorkCorrectly() {
            // Act
            sut.setSex(DEFAULT_SEX);

            // Assert
            assertEquals(DEFAULT_SEX, sut.getSex());
        }

        @ParameterizedTest(name = "Sex ''{0}'' should be accepted")
        @ValueSource(strings = { "M", "F", "Male", "Female", "ชาย", "หญิง" })
        @DisplayName("setSex should accept various sex values")
        void setSex_ShouldAcceptVariousValues(String sex) {
            // Act
            sut.setSex(sex);

            // Assert
            assertEquals(sex, sut.getSex());
        }

        @Test
        @DisplayName("setPersonalid and getPersonalid should work correctly")
        void setAndGetPersonalid_ShouldWorkCorrectly() {
            // Act
            sut.setPersonalid(DEFAULT_PERSONALID);

            // Assert
            assertEquals(DEFAULT_PERSONALID, sut.getPersonalid());
        }

        @Test
        @DisplayName("setPersonalid should handle 13-digit Thai ID")
        void setPersonalid_ShouldHandle13DigitThaiId() {
            // Arrange
            String thaiId = "1234567890123";

            // Act
            sut.setPersonalid(thaiId);

            // Assert
            assertEquals(thaiId, sut.getPersonalid());
        }

        @Test
        @DisplayName("setBirthday and getBirthday should work correctly")
        void setAndGetBirthday_ShouldWorkCorrectly() {
            // Act
            sut.setBirthday(DEFAULT_BIRTHDAY);

            // Assert
            assertEquals(DEFAULT_BIRTHDAY, sut.getBirthday());
        }

        @Test
        @DisplayName("setOccupation and getOccupation should work correctly")
        void setAndGetOccupation_ShouldWorkCorrectly() {
            // Act
            sut.setOccupation(DEFAULT_OCCUPATION);

            // Assert
            assertEquals(DEFAULT_OCCUPATION, sut.getOccupation());
        }

        @Test
        @DisplayName("setNationality and getNationality should work correctly")
        void setAndGetNationality_ShouldWorkCorrectly() {
            // Act
            sut.setNationality(DEFAULT_NATIONALITY);

            // Assert
            assertEquals(DEFAULT_NATIONALITY, sut.getNationality());
        }
    }

    // =========================================================================
    // Contact Information Tests
    // =========================================================================

    @Nested
    @DisplayName("Contact Information Tests")
    class ContactInfoTests {

        @Test
        @DisplayName("setAddress1 and getAddress1 should work correctly")
        void setAndGetAddress1_ShouldWorkCorrectly() {
            // Act
            sut.setAddress1(DEFAULT_ADDRESS1);

            // Assert
            assertEquals(DEFAULT_ADDRESS1, sut.getAddress1());
        }

        @Test
        @DisplayName("setAddress2 and getAddress2 should work correctly")
        void setAndGetAddress2_ShouldWorkCorrectly() {
            // Arrange
            String address2 = "Suite 100";

            // Act
            sut.setAddress2(address2);

            // Assert
            assertEquals(address2, sut.getAddress2());
        }

        @Test
        @DisplayName("setTel1 and getTel1 should work correctly")
        void setAndGetTel1_ShouldWorkCorrectly() {
            // Act
            sut.setTel1(DEFAULT_TEL1);

            // Assert
            assertEquals(DEFAULT_TEL1, sut.getTel1());
        }

        @ParameterizedTest(name = "Phone ''{0}'' should be accepted")
        @ValueSource(strings = { "0812345678", "02-123-4567", "+66812345678", "081-234-5678" })
        @DisplayName("setTel1 should accept various phone formats")
        void setTel1_ShouldAcceptVariousFormats(String phone) {
            // Act
            sut.setTel1(phone);

            // Assert
            assertEquals(phone, sut.getTel1());
        }

        @Test
        @DisplayName("setTel2 and getTel2 should work correctly")
        void setAndGetTel2_ShouldWorkCorrectly() {
            // Arrange
            String tel2 = "0891234567";

            // Act
            sut.setTel2(tel2);

            // Assert
            assertEquals(tel2, sut.getTel2());
        }

        @Test
        @DisplayName("setTel3 and getTel3 should work correctly")
        void setAndGetTel3_ShouldWorkCorrectly() {
            // Arrange
            String tel3 = "0861234567";

            // Act
            sut.setTel3(tel3);

            // Assert
            assertEquals(tel3, sut.getTel3());
        }

        @Test
        @DisplayName("setEmail and getEmail should work correctly")
        void setAndGetEmail_ShouldWorkCorrectly() {
            // Act
            sut.setEmail(DEFAULT_EMAIL);

            // Assert
            assertEquals(DEFAULT_EMAIL, sut.getEmail());
        }

        @ParameterizedTest(name = "Email ''{0}'' should be accepted")
        @ValueSource(strings = { "test@example.com", "user.name@domain.co.th", "a@b.c" })
        @DisplayName("setEmail should accept various email formats")
        void setEmail_ShouldAcceptVariousFormats(String email) {
            // Act
            sut.setEmail(email);

            // Assert
            assertEquals(email, sut.getEmail());
        }

        @Test
        @DisplayName("setProvince and getProvince should work correctly")
        void setAndGetProvince_ShouldWorkCorrectly() {
            // Arrange
            String province = "Bangkok";

            // Act
            sut.setProvince(province);

            // Assert
            assertEquals(province, sut.getProvince());
        }

        @Test
        @DisplayName("setZip and getZip should work correctly")
        void setAndGetZip_ShouldWorkCorrectly() {
            // Arrange
            String zip = "10110";

            // Act
            sut.setZip(zip);

            // Assert
            assertEquals(zip, sut.getZip());
        }
    }

    // =========================================================================
    // Salary Field Tests
    // =========================================================================

    @Nested
    @DisplayName("Salary Field Tests")
    class SalaryTests {

        @Test
        @DisplayName("setSalary and getSalary should work correctly")
        void setAndGetSalary_ShouldWorkCorrectly() {
            // Act
            sut.setSalary(DEFAULT_SALARY);

            // Assert
            assertEquals(DEFAULT_SALARY, sut.getSalary());
        }

        @Test
        @DisplayName("setSalary should handle zero value")
        void setSalary_ShouldHandleZeroValue() {
            // Arrange
            BigDecimal zero = BigDecimal.ZERO;

            // Act
            sut.setSalary(zero);

            // Assert
            assertEquals(zero, sut.getSalary());
        }

        @Test
        @DisplayName("setSalary should handle large values")
        void setSalary_ShouldHandleLargeValues() {
            // Arrange
            BigDecimal largeSalary = new BigDecimal("9999999.99");

            // Act
            sut.setSalary(largeSalary);

            // Assert
            assertEquals(largeSalary, sut.getSalary());
        }

        @Test
        @DisplayName("setSalary should accept null value")
        void setSalary_ShouldAcceptNull() {
            // Arrange
            sut.setSalary(DEFAULT_SALARY);

            // Act
            sut.setSalary(null);

            // Assert
            assertNull(sut.getSalary());
        }
    }

    // =========================================================================
    // DateTime Field Tests
    // =========================================================================

    @Nested
    @DisplayName("DateTime Field Tests")
    class DateTimeTests {

        @Test
        @DisplayName("setDat and getDat should work correctly")
        void setAndGetDat_ShouldWorkCorrectly() {
            // Act
            sut.setDat(DEFAULT_DAT);

            // Assert
            assertEquals(DEFAULT_DAT, sut.getDat());
        }

        @Test
        @DisplayName("setDat should accept null value")
        void setDat_ShouldAcceptNull() {
            // Arrange
            sut.setDat(DEFAULT_DAT);

            // Act
            sut.setDat(null);

            // Assert
            assertNull(sut.getDat());
        }

        @Test
        @DisplayName("setDat should handle different date times")
        void setDat_ShouldHandleDifferentDateTimes() {
            // Arrange
            LocalDateTime past = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
            LocalDateTime future = LocalDateTime.of(2050, 12, 31, 23, 59, 59);

            // Act & Assert
            sut.setDat(past);
            assertEquals(past, sut.getDat());

            sut.setDat(future);
            assertEquals(future, sut.getDat());
        }
    }

    // =========================================================================
    // Status Field Tests
    // =========================================================================

    @Nested
    @DisplayName("Status Field Tests")
    class StatusTests {

        @Test
        @DisplayName("setStatus and getStatus should work correctly")
        void setAndGetStatus_ShouldWorkCorrectly() {
            // Act
            sut.setStatus(DEFAULT_STATUS);

            // Assert
            assertEquals(DEFAULT_STATUS, sut.getStatus());
        }

        @ParameterizedTest(name = "Status ''{0}'' should be accepted")
        @ValueSource(strings = { "ACTIVE", "INACTIVE", "DISCHARGED", "DECEASED" })
        @DisplayName("setStatus should accept various status values")
        void setStatus_ShouldAcceptVariousValues(String status) {
            // Act
            sut.setStatus(status);

            // Assert
            assertEquals(status, sut.getStatus());
        }

        @Test
        @DisplayName("setStatus should allow status update")
        void setStatus_ShouldAllowStatusUpdate() {
            // Arrange
            sut.setStatus("ACTIVE");

            // Act
            sut.setStatus("INACTIVE");

            // Assert
            assertEquals("INACTIVE", sut.getStatus());
        }
    }

    // =========================================================================
    // Social Media Fields Tests
    // =========================================================================

    @Nested
    @DisplayName("Social Media Fields Tests")
    class SocialMediaTests {

        @Test
        @DisplayName("setFacebook and getFacebook should work correctly")
        void setAndGetFacebook_ShouldWorkCorrectly() {
            // Arrange
            String facebook = "john.doe.fb";

            // Act
            sut.setFacebook(facebook);

            // Assert
            assertEquals(facebook, sut.getFacebook());
        }

        @Test
        @DisplayName("setLine and getLine should work correctly")
        void setAndGetLine_ShouldWorkCorrectly() {
            // Arrange
            String line = "johndoe_line";

            // Act
            sut.setLine(line);

            // Assert
            assertEquals(line, sut.getLine());
        }
    }

    // =========================================================================
    // Medical Fields Tests
    // =========================================================================

    @Nested
    @DisplayName("Medical Fields Tests")
    class MedicalFieldsTests {

        @Test
        @DisplayName("setVn and getVn should work correctly")
        void setAndGetVn_ShouldWorkCorrectly() {
            // Arrange
            String vn = "VN001";

            // Act
            sut.setVn(vn);

            // Assert
            assertEquals(vn, sut.getVn());
        }

        @Test
        @DisplayName("setCn and getCn should work correctly")
        void setAndGetCn_ShouldWorkCorrectly() {
            // Arrange
            String cn = "CN001";

            // Act
            sut.setCn(cn);

            // Assert
            assertEquals(cn, sut.getCn());
        }

        @Test
        @DisplayName("setDinose and getDinose should work correctly")
        void setAndGetDinose_ShouldWorkCorrectly() {
            // Arrange
            String diagnosis = "Common Cold";

            // Act
            sut.setDinose(diagnosis);

            // Assert
            assertEquals(diagnosis, sut.getDinose());
        }

        @Test
        @DisplayName("setDrugeanti and getDrugeanti should work correctly")
        void setAndGetDrugeanti_ShouldWorkCorrectly() {
            // Arrange
            String drugAllergy = "Penicillin";

            // Act
            sut.setDrugeanti(drugAllergy);

            // Assert
            assertEquals(drugAllergy, sut.getDrugeanti());
        }

        @Test
        @DisplayName("setClinicname should work correctly")
        void setAndGetClinicname_ShouldWorkCorrectly() {
            // Arrange
            String clinicName = "General Clinic";

            // Act
            sut.setClinicname(clinicName);

            // Assert
            assertEquals(clinicName, sut.getClinicname());
        }
    }

    // =========================================================================
    // Physical Measurements Tests
    // =========================================================================

    @Nested
    @DisplayName("Physical Measurements Tests")
    class PhysicalMeasurementsTests {

        @Test
        @DisplayName("setWg and getWg should work correctly (weight)")
        void setAndGetWg_ShouldWorkCorrectly() {
            // Arrange
            String weight = "70";

            // Act
            sut.setWg(weight);

            // Assert
            assertEquals(weight, sut.getWg());
        }

        @Test
        @DisplayName("setHg and getHg should work correctly (height)")
        void setAndGetHg_ShouldWorkCorrectly() {
            // Arrange
            String height = "175";

            // Act
            sut.setHg(height);

            // Assert
            assertEquals(height, sut.getHg());
        }

        @Test
        @DisplayName("setSkin and getSkin should work correctly")
        void setAndGetSkin_ShouldWorkCorrectly() {
            // Arrange
            String skin = "Normal";

            // Act
            sut.setSkin(skin);

            // Assert
            assertEquals(skin, sut.getSkin());
        }
    }

    // =========================================================================
    // Version Field Tests (for optimistic locking)
    // =========================================================================

    @Nested
    @DisplayName("Version Field Tests")
    class VersionTests {

        @Test
        @DisplayName("setVersion and getVersion should work correctly")
        void setAndGetVersion_ShouldWorkCorrectly() {
            // Arrange
            Long version = 1L;

            // Act
            sut.setVersion(version);

            // Assert
            assertEquals(version, sut.getVersion());
        }

        @Test
        @DisplayName("setVersion should accept null value")
        void setVersion_ShouldAcceptNull() {
            // Arrange
            sut.setVersion(1L);

            // Act
            sut.setVersion(null);

            // Assert
            assertNull(sut.getVersion());
        }
    }

    // =========================================================================
    // Other Fields Tests
    // =========================================================================

    @Nested
    @DisplayName("Other Fields Tests")
    class OtherFieldsTests {

        @Test
        @DisplayName("setMode and getMode should work correctly")
        void setAndGetMode_ShouldWorkCorrectly() {
            // Arrange
            String mode = "NEW";

            // Act
            sut.setMode(mode);

            // Assert
            assertEquals(mode, sut.getMode());
        }

        @Test
        @DisplayName("setLevel and getLevel should work correctly")
        void setAndGetLevel_ShouldWorkCorrectly() {
            // Arrange
            String level = "VIP";

            // Act
            sut.setLevel(level);

            // Assert
            assertEquals(level, sut.getLevel());
        }

        @Test
        @DisplayName("setImage and getImage should work correctly")
        void setAndGetImage_ShouldWorkCorrectly() {
            // Arrange
            String image = "/images/patients/john_doe.jpg";

            // Act
            sut.setImage(image);

            // Assert
            assertEquals(image, sut.getImage());
        }

        @Test
        @DisplayName("setEmpid and getEmpid should work correctly")
        void setAndGetEmpid_ShouldWorkCorrectly() {
            // Arrange
            String empid = "EMP001";

            // Act
            sut.setEmpid(empid);

            // Assert
            assertEquals(empid, sut.getEmpid());
        }

        @Test
        @DisplayName("setTyp and getTyp should work correctly")
        void setAndGetTyp_ShouldWorkCorrectly() {
            // Arrange
            String typ = "OPD";

            // Act
            sut.setTyp(typ);

            // Assert
            assertEquals(typ, sut.getTyp());
        }
    }

    // =========================================================================
    // Integration Tests
    // =========================================================================

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Fully populated Patient should return correct values")
        void fullyPopulatedPatient_ShouldReturnCorrectValues() {
            // Arrange
            sut.setId(DEFAULT_ID);
            sut.setHn(DEFAULT_HN);
            sut.setPname(DEFAULT_PNAME);
            sut.setFname(DEFAULT_FNAME);
            sut.setLname(DEFAULT_LNAME);
            sut.setNname(DEFAULT_NNAME);
            sut.setSex(DEFAULT_SEX);
            sut.setPersonalid(DEFAULT_PERSONALID);
            sut.setBirthday(DEFAULT_BIRTHDAY);
            sut.setOccupation(DEFAULT_OCCUPATION);
            sut.setNationality(DEFAULT_NATIONALITY);
            sut.setAddress1(DEFAULT_ADDRESS1);
            sut.setTel1(DEFAULT_TEL1);
            sut.setEmail(DEFAULT_EMAIL);
            sut.setSalary(DEFAULT_SALARY);
            sut.setDat(DEFAULT_DAT);
            sut.setStatus(DEFAULT_STATUS);

            // Assert
            assertAll("All key fields should be correctly set",
                    () -> assertEquals(DEFAULT_ID, sut.getId()),
                    () -> assertEquals(DEFAULT_HN, sut.getHn()),
                    () -> assertEquals(DEFAULT_PNAME, sut.getPname()),
                    () -> assertEquals(DEFAULT_FNAME, sut.getFname()),
                    () -> assertEquals(DEFAULT_LNAME, sut.getLname()),
                    () -> assertEquals(DEFAULT_NNAME, sut.getNname()),
                    () -> assertEquals(DEFAULT_SEX, sut.getSex()),
                    () -> assertEquals(DEFAULT_PERSONALID, sut.getPersonalid()),
                    () -> assertEquals(DEFAULT_BIRTHDAY, sut.getBirthday()),
                    () -> assertEquals(DEFAULT_OCCUPATION, sut.getOccupation()),
                    () -> assertEquals(DEFAULT_NATIONALITY, sut.getNationality()),
                    () -> assertEquals(DEFAULT_ADDRESS1, sut.getAddress1()),
                    () -> assertEquals(DEFAULT_TEL1, sut.getTel1()),
                    () -> assertEquals(DEFAULT_EMAIL, sut.getEmail()),
                    () -> assertEquals(DEFAULT_SALARY, sut.getSalary()),
                    () -> assertEquals(DEFAULT_DAT, sut.getDat()),
                    () -> assertEquals(DEFAULT_STATUS, sut.getStatus()));
        }

        @Test
        @DisplayName("Patient should allow field updates")
        void patient_ShouldAllowFieldUpdates() {
            // Arrange
            sut.setFname("Original");
            sut.setLname("Name");
            sut.setStatus("ACTIVE");

            // Act
            sut.setFname("Updated");
            sut.setLname("NewName");
            sut.setStatus("INACTIVE");

            // Assert
            assertAll("Fields should be updated",
                    () -> assertEquals("Updated", sut.getFname()),
                    () -> assertEquals("NewName", sut.getLname()),
                    () -> assertEquals("INACTIVE", sut.getStatus()));
        }

        @Test
        @DisplayName("Patient should implement Serializable")
        void patient_ShouldImplementSerializable() {
            // Assert
            assertTrue(sut instanceof java.io.Serializable);
        }
    }
}
