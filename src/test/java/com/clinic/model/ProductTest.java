package com.clinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Product entity class.
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
@DisplayName("Product Entity Tests")
class ProductTest {

    // SUT (System Under Test)
    private Product sut;

    // Test fixtures (constants)
    private static final String DEFAULT_DID = "D001";
    private static final String DEFAULT_BARCODE = "1234567890123";
    private static final String DEFAULT_GNAME = "Paracetamol";
    private static final String DEFAULT_TNAME = "พาราเซตามอล";
    private static final String DEFAULT_UNIT = "Tablet";
    private static final String DEFAULT_BUNIT = "Box";
    private static final Integer DEFAULT_UQTY = 10;
    private static final String DEFAULT_DGID = "DG001";
    private static final String DEFAULT_DGROUP = "Pain Relief";
    private static final String DEFAULT_TID = "T001";
    private static final String DEFAULT_TYPNAME = "Medicine";
    private static final BigDecimal DEFAULT_BPRICE = new BigDecimal("100.00");
    private static final BigDecimal DEFAULT_TPRICE = new BigDecimal("120.00");
    private static final BigDecimal DEFAULT_SPRICE = new BigDecimal("150.00");
    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal("1500.00");
    private static final Integer DEFAULT_SQTY = 100;
    private static final String DEFAULT_DUSE = "Take 1-2 tablets every 4-6 hours";
    private static final String DEFAULT_STATUS = "ACTIVE";

    @BeforeEach
    void setUp() {
        sut = new Product();
    }

    // =========================================================================
    // Constructor Tests
    // =========================================================================

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Default constructor should create Product with all null fields")
        void defaultConstructor_ShouldCreateProductWithNullFields() {
            // Act - already done in @BeforeEach

            // Assert - checking key fields
            assertAll("All key fields should be null",
                    () -> assertNull(sut.getDid()),
                    () -> assertNull(sut.getBarcode()),
                    () -> assertNull(sut.getGname()),
                    () -> assertNull(sut.getTname()),
                    () -> assertNull(sut.getUnit()),
                    () -> assertNull(sut.getBprice()),
                    () -> assertNull(sut.getSprice()),
                    () -> assertNull(sut.getStatus()));
        }
    }

    // =========================================================================
    // ID Field Tests (did)
    // =========================================================================

    @Nested
    @DisplayName("DID (Product ID) Field Tests")
    class DidTests {

        @Test
        @DisplayName("setDid and getDid should work correctly")
        void setAndGetDid_ShouldWorkCorrectly() {
            // Act
            sut.setDid(DEFAULT_DID);

            // Assert
            assertEquals(DEFAULT_DID, sut.getDid());
        }

        @ParameterizedTest(name = "DID ''{0}'' should be accepted")
        @ValueSource(strings = { "D001", "PROD-12345", "ABC", "123" })
        @DisplayName("setDid should accept various valid IDs")
        void setDid_ShouldAcceptValidIds(String did) {
            // Act
            sut.setDid(did);

            // Assert
            assertEquals(did, sut.getDid());
        }

        @ParameterizedTest(name = "DID should accept null/empty: ''{0}''")
        @NullAndEmptySource
        @DisplayName("setDid should accept null and empty values")
        void setDid_ShouldAcceptNullAndEmpty(String did) {
            // Act
            sut.setDid(did);

            // Assert
            assertEquals(did, sut.getDid());
        }
    }

    // =========================================================================
    // Barcode Field Tests
    // =========================================================================

    @Nested
    @DisplayName("Barcode Field Tests")
    class BarcodeTests {

        @Test
        @DisplayName("setBarcode and getBarcode should work correctly")
        void setAndGetBarcode_ShouldWorkCorrectly() {
            // Act
            sut.setBarcode(DEFAULT_BARCODE);

            // Assert
            assertEquals(DEFAULT_BARCODE, sut.getBarcode());
        }

        @ParameterizedTest(name = "Barcode ''{0}'' should be accepted")
        @ValueSource(strings = { "1234567890123", "ABC123456789", "0000000000000" })
        @DisplayName("setBarcode should accept various barcode formats")
        void setBarcode_ShouldAcceptValidBarcodes(String barcode) {
            // Act
            sut.setBarcode(barcode);

            // Assert
            assertEquals(barcode, sut.getBarcode());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("setBarcode should accept null and empty values")
        void setBarcode_ShouldAcceptNullAndEmpty(String barcode) {
            // Act
            sut.setBarcode(barcode);

            // Assert
            assertEquals(barcode, sut.getBarcode());
        }
    }

    // =========================================================================
    // Name Fields Tests (gname, tname)
    // =========================================================================

    @Nested
    @DisplayName("Product Name Fields Tests")
    class NameTests {

        @Test
        @DisplayName("setGname and getGname should work correctly")
        void setAndGetGname_ShouldWorkCorrectly() {
            // Act
            sut.setGname(DEFAULT_GNAME);

            // Assert
            assertEquals(DEFAULT_GNAME, sut.getGname());
        }

        @Test
        @DisplayName("setTname and getTname should work correctly")
        void setAndGetTname_ShouldWorkCorrectly() {
            // Act
            sut.setTname(DEFAULT_TNAME);

            // Assert
            assertEquals(DEFAULT_TNAME, sut.getTname());
        }

        @Test
        @DisplayName("setTname should handle Thai characters")
        void setTname_ShouldHandleThaiCharacters() {
            // Arrange
            String thaiName = "ยาแก้ปวด พาราเซตามอล 500mg";

            // Act
            sut.setTname(thaiName);

            // Assert
            assertEquals(thaiName, sut.getTname());
        }

        @Test
        @DisplayName("setGname should handle special characters")
        void setGname_ShouldHandleSpecialCharacters() {
            // Arrange
            String specialName = "Drug (Generic) - 500mg/ml";

            // Act
            sut.setGname(specialName);

            // Assert
            assertEquals(specialName, sut.getGname());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("setGname should accept null and empty values")
        void setGname_ShouldAcceptNullAndEmpty(String name) {
            // Act
            sut.setGname(name);

            // Assert
            assertEquals(name, sut.getGname());
        }
    }

    // =========================================================================
    // Unit Fields Tests (unit, bunit, uqty)
    // =========================================================================

    @Nested
    @DisplayName("Unit Fields Tests")
    class UnitTests {

        @Test
        @DisplayName("setUnit and getUnit should work correctly")
        void setAndGetUnit_ShouldWorkCorrectly() {
            // Act
            sut.setUnit(DEFAULT_UNIT);

            // Assert
            assertEquals(DEFAULT_UNIT, sut.getUnit());
        }

        @Test
        @DisplayName("setBunit and getBunit should work correctly")
        void setAndGetBunit_ShouldWorkCorrectly() {
            // Act
            sut.setBunit(DEFAULT_BUNIT);

            // Assert
            assertEquals(DEFAULT_BUNIT, sut.getBunit());
        }

        @Test
        @DisplayName("setUqty and getUqty should work correctly")
        void setAndGetUqty_ShouldWorkCorrectly() {
            // Act
            sut.setUqty(DEFAULT_UQTY);

            // Assert
            assertEquals(DEFAULT_UQTY, sut.getUqty());
        }

        @ParameterizedTest(name = "Unit quantity {0} should be accepted")
        @ValueSource(ints = { 0, 1, 10, 100, Integer.MAX_VALUE })
        @DisplayName("setUqty should accept various integer values")
        void setUqty_ShouldAcceptVariousValues(Integer qty) {
            // Act
            sut.setUqty(qty);

            // Assert
            assertEquals(qty, sut.getUqty());
        }

        @Test
        @DisplayName("setUqty should accept null value")
        void setUqty_ShouldAcceptNull() {
            // Arrange
            sut.setUqty(DEFAULT_UQTY);

            // Act
            sut.setUqty(null);

            // Assert
            assertNull(sut.getUqty());
        }
    }

    // =========================================================================
    // Group Fields Tests (dgid, dgroup)
    // =========================================================================

    @Nested
    @DisplayName("Group Fields Tests")
    class GroupTests {

        @Test
        @DisplayName("setDgid and getDgid should work correctly")
        void setAndGetDgid_ShouldWorkCorrectly() {
            // Act
            sut.setDgid(DEFAULT_DGID);

            // Assert
            assertEquals(DEFAULT_DGID, sut.getDgid());
        }

        @Test
        @DisplayName("setDgroup and getDgroup should work correctly")
        void setAndGetDgroup_ShouldWorkCorrectly() {
            // Act
            sut.setDgroup(DEFAULT_DGROUP);

            // Assert
            assertEquals(DEFAULT_DGROUP, sut.getDgroup());
        }

        @ParameterizedTest(name = "Drug group ''{0}'' should be accepted")
        @ValueSource(strings = { "Pain Relief", "Antibiotics", "Vitamins", "First Aid" })
        @DisplayName("setDgroup should accept various group names")
        void setDgroup_ShouldAcceptVariousGroups(String group) {
            // Act
            sut.setDgroup(group);

            // Assert
            assertEquals(group, sut.getDgroup());
        }
    }

    // =========================================================================
    // Type Fields Tests (tid, typname)
    // =========================================================================

    @Nested
    @DisplayName("Type Fields Tests")
    class TypeTests {

        @Test
        @DisplayName("setTid and getTid should work correctly")
        void setAndGetTid_ShouldWorkCorrectly() {
            // Act
            sut.setTid(DEFAULT_TID);

            // Assert
            assertEquals(DEFAULT_TID, sut.getTid());
        }

        @Test
        @DisplayName("setTypname and getTypname should work correctly")
        void setAndGetTypname_ShouldWorkCorrectly() {
            // Act
            sut.setTypname(DEFAULT_TYPNAME);

            // Assert
            assertEquals(DEFAULT_TYPNAME, sut.getTypname());
        }

        @ParameterizedTest(name = "Type name ''{0}'' should be accepted")
        @ValueSource(strings = { "Medicine", "Equipment", "Supply", "Consumable" })
        @DisplayName("setTypname should accept various type names")
        void setTypname_ShouldAcceptVariousTypes(String typname) {
            // Act
            sut.setTypname(typname);

            // Assert
            assertEquals(typname, sut.getTypname());
        }
    }

    // =========================================================================
    // Price Fields Tests (bprice, tprice, sprice, total)
    // =========================================================================

    @Nested
    @DisplayName("Price Fields Tests")
    class PriceTests {

        @Test
        @DisplayName("setBprice and getBprice should work correctly")
        void setAndGetBprice_ShouldWorkCorrectly() {
            // Act
            sut.setBprice(DEFAULT_BPRICE);

            // Assert
            assertEquals(DEFAULT_BPRICE, sut.getBprice());
        }

        @Test
        @DisplayName("setTprice and getTprice should work correctly")
        void setAndGetTprice_ShouldWorkCorrectly() {
            // Act
            sut.setTprice(DEFAULT_TPRICE);

            // Assert
            assertEquals(DEFAULT_TPRICE, sut.getTprice());
        }

        @Test
        @DisplayName("setSprice and getSprice should work correctly")
        void setAndGetSprice_ShouldWorkCorrectly() {
            // Act
            sut.setSprice(DEFAULT_SPRICE);

            // Assert
            assertEquals(DEFAULT_SPRICE, sut.getSprice());
        }

        @Test
        @DisplayName("setTotal and getTotal should work correctly")
        void setAndGetTotal_ShouldWorkCorrectly() {
            // Act
            sut.setTotal(DEFAULT_TOTAL);

            // Assert
            assertEquals(DEFAULT_TOTAL, sut.getTotal());
        }

        @Test
        @DisplayName("Price fields should handle zero values")
        void priceFields_ShouldHandleZeroValues() {
            // Arrange
            BigDecimal zero = BigDecimal.ZERO;

            // Act
            sut.setBprice(zero);
            sut.setTprice(zero);
            sut.setSprice(zero);
            sut.setTotal(zero);

            // Assert
            assertAll("All price fields should be zero",
                    () -> assertEquals(zero, sut.getBprice()),
                    () -> assertEquals(zero, sut.getTprice()),
                    () -> assertEquals(zero, sut.getSprice()),
                    () -> assertEquals(zero, sut.getTotal()));
        }

        @Test
        @DisplayName("Price fields should handle large decimal values")
        void priceFields_ShouldHandleLargeDecimalValues() {
            // Arrange
            BigDecimal largePrice = new BigDecimal("999999999.99");

            // Act
            sut.setSprice(largePrice);

            // Assert
            assertEquals(largePrice, sut.getSprice());
        }

        @Test
        @DisplayName("Price fields should handle decimal precision")
        void priceFields_ShouldHandleDecimalPrecision() {
            // Arrange
            BigDecimal precisePrice = new BigDecimal("123.456789");

            // Act
            sut.setBprice(precisePrice);

            // Assert
            assertEquals(precisePrice, sut.getBprice());
        }

        @Test
        @DisplayName("Price fields should accept null values")
        void priceFields_ShouldAcceptNullValues() {
            // Arrange
            sut.setBprice(DEFAULT_BPRICE);

            // Act
            sut.setBprice(null);

            // Assert
            assertNull(sut.getBprice());
        }
    }

    // =========================================================================
    // Quantity Field Tests (sqty)
    // =========================================================================

    @Nested
    @DisplayName("Stock Quantity (sqty) Field Tests")
    class SqtyTests {

        @Test
        @DisplayName("setSqty and getSqty should work correctly")
        void setAndGetSqty_ShouldWorkCorrectly() {
            // Act
            sut.setSqty(DEFAULT_SQTY);

            // Assert
            assertEquals(DEFAULT_SQTY, sut.getSqty());
        }

        @ParameterizedTest(name = "Stock quantity {0} should be accepted")
        @ValueSource(ints = { 0, 1, 100, 1000, Integer.MAX_VALUE })
        @DisplayName("setSqty should accept various integer values")
        void setSqty_ShouldAcceptVariousValues(Integer qty) {
            // Act
            sut.setSqty(qty);

            // Assert
            assertEquals(qty, sut.getSqty());
        }

        @Test
        @DisplayName("setSqty should accept null value")
        void setSqty_ShouldAcceptNull() {
            // Arrange
            sut.setSqty(DEFAULT_SQTY);

            // Act
            sut.setSqty(null);

            // Assert
            assertNull(sut.getSqty());
        }
    }

    // =========================================================================
    // Usage Fields Tests (duse, duseL1, duseL2, wuse, huse)
    // =========================================================================

    @Nested
    @DisplayName("Usage Fields Tests")
    class UsageTests {

        @Test
        @DisplayName("setDuse and getDuse should work correctly")
        void setAndGetDuse_ShouldWorkCorrectly() {
            // Act
            sut.setDuse(DEFAULT_DUSE);

            // Assert
            assertEquals(DEFAULT_DUSE, sut.getDuse());
        }

        @Test
        @DisplayName("setDuseL1 and getDuseL1 should work correctly")
        void setAndGetDuseL1_ShouldWorkCorrectly() {
            // Arrange
            String duseL1 = "Morning dose";

            // Act
            sut.setDuseL1(duseL1);

            // Assert
            assertEquals(duseL1, sut.getDuseL1());
        }

        @Test
        @DisplayName("setDuseL2 and getDuseL2 should work correctly")
        void setAndGetDuseL2_ShouldWorkCorrectly() {
            // Arrange
            String duseL2 = "Evening dose";

            // Act
            sut.setDuseL2(duseL2);

            // Assert
            assertEquals(duseL2, sut.getDuseL2());
        }

        @Test
        @DisplayName("setWuse and getWuse should work correctly")
        void setAndGetWuse_ShouldWorkCorrectly() {
            // Arrange
            String wuse = "Weekly usage instructions";

            // Act
            sut.setWuse(wuse);

            // Assert
            assertEquals(wuse, sut.getWuse());
        }

        @Test
        @DisplayName("setHuse and getHuse should work correctly")
        void setAndGetHuse_ShouldWorkCorrectly() {
            // Arrange
            String huse = "Hospital usage instructions";

            // Act
            sut.setHuse(huse);

            // Assert
            assertEquals(huse, sut.getHuse());
        }

        @Test
        @DisplayName("Usage fields should handle long text")
        void usageFields_ShouldHandleLongText() {
            // Arrange
            String longText = "Take 1-2 tablets every 4-6 hours as needed. " +
                    "Do not exceed 8 tablets in 24 hours. " +
                    "Consult your doctor if symptoms persist.";

            // Act
            sut.setDuse(longText);

            // Assert
            assertEquals(longText, sut.getDuse());
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
        @ValueSource(strings = { "ACTIVE", "INACTIVE", "DISCONTINUED", "OUT_OF_STOCK" })
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
    // Other Fields Tests (dsize, vat, dc, ec, img, typ)
    // =========================================================================

    @Nested
    @DisplayName("Other Fields Tests")
    class OtherFieldsTests {

        @Test
        @DisplayName("setDsize and getDsize should work correctly")
        void setAndGetDsize_ShouldWorkCorrectly() {
            // Arrange
            String dsize = "500mg";

            // Act
            sut.setDsize(dsize);

            // Assert
            assertEquals(dsize, sut.getDsize());
        }

        @Test
        @DisplayName("setVat and getVat should work correctly")
        void setAndGetVat_ShouldWorkCorrectly() {
            // Arrange
            String vat = "7";

            // Act
            sut.setVat(vat);

            // Assert
            assertEquals(vat, sut.getVat());
        }

        @Test
        @DisplayName("setDc and getDc should work correctly")
        void setAndGetDc_ShouldWorkCorrectly() {
            // Arrange
            String dc = "DC001";

            // Act
            sut.setDc(dc);

            // Assert
            assertEquals(dc, sut.getDc());
        }

        @Test
        @DisplayName("setEc and getEc should work correctly")
        void setAndGetEc_ShouldWorkCorrectly() {
            // Arrange
            String ec = "EC001";

            // Act
            sut.setEc(ec);

            // Assert
            assertEquals(ec, sut.getEc());
        }

        @Test
        @DisplayName("setImg and getImg should work correctly")
        void setAndGetImg_ShouldWorkCorrectly() {
            // Arrange
            String img = "/images/products/paracetamol.jpg";

            // Act
            sut.setImg(img);

            // Assert
            assertEquals(img, sut.getImg());
        }

        @Test
        @DisplayName("setTyp and getTyp should work correctly")
        void setAndGetTyp_ShouldWorkCorrectly() {
            // Arrange
            String typ = "MED";

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
        @DisplayName("Fully populated Product should return correct values")
        void fullyPopulatedProduct_ShouldReturnCorrectValues() {
            // Arrange
            sut.setDid(DEFAULT_DID);
            sut.setBarcode(DEFAULT_BARCODE);
            sut.setGname(DEFAULT_GNAME);
            sut.setTname(DEFAULT_TNAME);
            sut.setUnit(DEFAULT_UNIT);
            sut.setBunit(DEFAULT_BUNIT);
            sut.setUqty(DEFAULT_UQTY);
            sut.setDgid(DEFAULT_DGID);
            sut.setDgroup(DEFAULT_DGROUP);
            sut.setTid(DEFAULT_TID);
            sut.setTypname(DEFAULT_TYPNAME);
            sut.setBprice(DEFAULT_BPRICE);
            sut.setTprice(DEFAULT_TPRICE);
            sut.setSprice(DEFAULT_SPRICE);
            sut.setTotal(DEFAULT_TOTAL);
            sut.setSqty(DEFAULT_SQTY);
            sut.setDuse(DEFAULT_DUSE);
            sut.setStatus(DEFAULT_STATUS);

            // Assert
            assertAll("All key fields should be correctly set",
                    () -> assertEquals(DEFAULT_DID, sut.getDid()),
                    () -> assertEquals(DEFAULT_BARCODE, sut.getBarcode()),
                    () -> assertEquals(DEFAULT_GNAME, sut.getGname()),
                    () -> assertEquals(DEFAULT_TNAME, sut.getTname()),
                    () -> assertEquals(DEFAULT_UNIT, sut.getUnit()),
                    () -> assertEquals(DEFAULT_BUNIT, sut.getBunit()),
                    () -> assertEquals(DEFAULT_UQTY, sut.getUqty()),
                    () -> assertEquals(DEFAULT_DGID, sut.getDgid()),
                    () -> assertEquals(DEFAULT_DGROUP, sut.getDgroup()),
                    () -> assertEquals(DEFAULT_TID, sut.getTid()),
                    () -> assertEquals(DEFAULT_TYPNAME, sut.getTypname()),
                    () -> assertEquals(DEFAULT_BPRICE, sut.getBprice()),
                    () -> assertEquals(DEFAULT_TPRICE, sut.getTprice()),
                    () -> assertEquals(DEFAULT_SPRICE, sut.getSprice()),
                    () -> assertEquals(DEFAULT_TOTAL, sut.getTotal()),
                    () -> assertEquals(DEFAULT_SQTY, sut.getSqty()),
                    () -> assertEquals(DEFAULT_DUSE, sut.getDuse()),
                    () -> assertEquals(DEFAULT_STATUS, sut.getStatus()));
        }

        @Test
        @DisplayName("Product should allow field updates")
        void product_ShouldAllowFieldUpdates() {
            // Arrange
            sut.setGname("Original Name");
            sut.setSprice(new BigDecimal("100.00"));
            sut.setStatus("ACTIVE");

            // Act
            sut.setGname("Updated Name");
            sut.setSprice(new BigDecimal("150.00"));
            sut.setStatus("DISCONTINUED");

            // Assert
            assertAll("Fields should be updated",
                    () -> assertEquals("Updated Name", sut.getGname()),
                    () -> assertEquals(new BigDecimal("150.00"), sut.getSprice()),
                    () -> assertEquals("DISCONTINUED", sut.getStatus()));
        }

        @Test
        @DisplayName("Product should implement Serializable")
        void product_ShouldImplementSerializable() {
            // Assert
            assertTrue(sut instanceof java.io.Serializable);
        }
    }
}
