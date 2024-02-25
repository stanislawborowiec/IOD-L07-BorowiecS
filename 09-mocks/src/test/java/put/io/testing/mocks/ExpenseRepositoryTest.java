package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    @Test
    void testLoadExpenses()
    {
        IFancyDatabase myDB = mock(MyDatabase.class);

        when(myDB.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository expences = new ExpenseRepository(myDB);
        expences.loadExpenses();

        InOrder inOrder=inOrder(myDB);
        inOrder.verify(myDB).connect();
        inOrder.verify(myDB).queryAll();
        inOrder.verify(myDB).close();

        assertTrue(expences.getExpenses().isEmpty());
    }

    @Test
    void testLoadExpenses2()
    {
        IFancyDatabase myDB = mock(MyDatabase.class);

        when(myDB.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository expences = new ExpenseRepository(myDB);
        Expense expense = new Expense();
        expences.loadExpenses();

        for (int i =0; i<5; i++)
        {
            expences.addExpense(expense);
        }

        expences.saveExpenses();

        verify(myDB,times(5)).persist(any(Expense.class));
    }
}
