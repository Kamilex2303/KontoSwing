class Konto{
    private int stan;
    int[] historia = new int[5];
    static int licznik = 0;
    Konto() { stan = 0; }
    public void operacja(int ile) throws DebetException {
        if (stan + ile >= 0 )
            stan += ile;
        else
            throw new DebetException("\n!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                                     "Przekroczono limit konta\n" +
                                     "!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    public int dajStan() { return stan ; }

}