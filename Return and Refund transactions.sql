/*Return and Refund transactions*/
SELECT 
    rt.ReturnID,
    rt.ReturnDate,
    p.ProductID,
    p.ProductName,
    rt.Quantity,
    rt.Reason,
    rt.RefundAmount,
    s.StaffID,
    s.Name AS StaffName
FROM ReturnTransaction rt
JOIN Product p ON rt.ProductID = p.ProductID
JOIN Staff s ON rt.StaffID = s.StaffID
ORDER BY rt.ReturnDate DESC;

/*Total Refund Amount per Staff*/
SELECT 
    s.StaffID,
    s.Name,
    SUM(rt.RefundAmount) AS TotalRefundsHandled
FROM ReturnTransaction rt
JOIN Staff s ON rt.StaffID = s.StaffID
GROUP BY s.StaffID, s.Name
ORDER BY TotalRefundsHandled DESC;

/*Most returned products*/
SELECT 
    p.ProductID,
    p.ProductName,
    SUM(rt.Quantity) AS TotalReturned
FROM ReturnTransaction rt
JOIN Product p ON rt.ProductID = p.ProductID
GROUP BY p.ProductID, p.ProductName
ORDER BY TotalReturned DESC;

/*Return within a date range*/
SELECT 
    rt.*,
    p.ProductName,
    s.Name AS StaffName
FROM ReturnTransaction rt
JOIN Product p ON rt.ProductID = p.ProductID
JOIN Staff s ON rt.StaffID = s.StaffID
WHERE rt.ReturnDate BETWEEN '2025-11-01' AND '2025-11-30';

