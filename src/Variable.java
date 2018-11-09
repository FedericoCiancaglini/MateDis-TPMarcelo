class Variable {
    Type type;
    Object value;
    String id;

    Variable(Object id, Object value) {
        this.id = (String) id;
        this.value = value;
        this.type = Type.INT;
    }

    @Override
    public String toString() {
        return type.toString() + " " + value;
    }
}
