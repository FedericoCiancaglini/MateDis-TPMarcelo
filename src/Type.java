class Type {

    public final static Type INT = new Type("int");
    public final static Type STRING = new Type("str");

    private String name;

    Type(String string) {
        this.name = string;
    }

    @Override
    public String toString() {
        return name;
    }
}
