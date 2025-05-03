package se.kth.iv1350.cashiersystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.cashiersystem.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private static Item item;

    @BeforeEach
    public void createNewItem() {
        item = new Item("123", "Bobs Hallonsylt", 14.90f, "TASTY", 10);
    }

    @AfterEach
    public void tearDown() {
        item = null;
    }

    @Test
    public void testItemInitialization() {
        assertEquals("123", item.getId(), "ID should be set correctly.");
    }

    @Test
    void testAddQuantity() {
        item.addQuantity(3);
        assertEquals(4, item.getQuantity(), "Quantity should increase correctly after adding.");
    }

    @Test
    void testToDTO() {
        ItemDTO dto = item.toDTO();
        assertEquals(item.getId(), dto.id(), "DTO ID should match Item ID.");
    }
}



