class Konto{
    private int stan;
    Konto() { stan = 0; }
    public void operacja(int ile) throws DebetException {
        if (stan + ile >= 0 )
            stan += ile;
        else
            throw new DebetException("\n!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                                     "Przekroczono limit konta\n" +
                                     "!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }
    public int dajStan() { return stan ; }
}