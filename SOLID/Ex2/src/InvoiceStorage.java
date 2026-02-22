public interface InvoiceStorage {
    void save(String invoiceId, String content);
    int countLines(String invoiceId);
}
