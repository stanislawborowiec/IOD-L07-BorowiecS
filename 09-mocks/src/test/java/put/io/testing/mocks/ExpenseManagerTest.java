package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    @Test
    public void testCalculateTotal() 
    {
        ExpenseRepository ExRep = mock(ExpenseRepository.class);
        
        FancyService FanServ = mock(FancyService.class);

        when(ExRep.getExpenses()).thenReturn(List.of(
                new Expense("Expence", "Category", 10),
                new Expense("Expence", "Category", 20),
                new Expense("Expence", "Category", 30)
        ));

        ExpenseManager ExMan = new ExpenseManager(ExRep, FanServ);
        
        long res = ExMan.calculateTotal();

        verify(ExRep).getExpenses();
        
        assertEquals(60, res);
    }

    @Test
    public void testCalculateTotalForCategory() 
    {
        ExpenseRepository ExRep = mock(ExpenseRepository.class);
        
        FancyService FanServ = mock(FancyService.class);

        when(ExRep.getExpensesByCategory("Home")).thenReturn(List.of(
                new Expense("Expence", "Home", 10),
                new Expense("Expence", "Home", 20),
                new Expense("Expence", "Home", 30)
        ));
        
        when(ExRep.getExpensesByCategory("Car")).thenReturn(List.of(
                new Expense("Expence", "Car", 10),
                new Expense("Expence", "Car", 20),
                new Expense("Expence", "Car", 30)
        ));
        
        when(ExRep.getExpensesByCategory("Food")).thenReturn(Collections.emptyList());
        
        when(ExRep.getExpensesByCategory("Sport")).thenReturn(Collections.emptyList());

        ExpenseManager ExMan = new ExpenseManager(ExRep, FanServ);
        
        long res = ExMan.calculateTotalForCategory("Home");

        verify(ExRep).getExpensesByCategory("Home");
        assertEquals(60, res);

        res = ExMan.calculateTotalForCategory("Car");

        verify(ExRep).getExpensesByCategory("Car");
        assertEquals(60, res);

        res = ExMan.calculateTotalForCategory("Food");

        verify(ExRep).getExpensesByCategory("Food");
        assertEquals(0, res);

        res = ExMan.calculateTotalForCategory("Sport");

        verify(ExRep).getExpensesByCategory("Sport");
        assertEquals(0, res);
    }

    // 5.1 Nie ma znaczenia
    @Test
    public void testCalculateTotalInDollars() throws ConnectException 
    {
        ExpenseRepository ExRep = mock(ExpenseRepository.class);
        
        FancyService FanServ = mock(FancyService.class);

        Expense Exp = new Expense();
        Exp.setTitle("Expence");
        Exp.setCategory("Category");
        Exp.setAmount(40);

        List<Expense> Expences = new ArrayList<Expense>();
        Expences.add(Exp);
        
        when(ExRep.getExpenses()).thenReturn(Expences);
        
        when(FanServ.convert(anyDouble(), eq("PLN"),eq("USD"))).thenAnswer((Answer) invocation ->
        {
            Object[] args = invocation.getArguments();
            return (double) args[0] / 4;
        });

        ExpenseManager ExMan = new ExpenseManager(ExRep, FanServ);
        
        double conv = ExMan.calculateTotalInDollars();

        verify(FanServ).convert(anyDouble(), eq("PLN"), eq("USD"));
        
        assertEquals(10.0, conv);
    }
}
