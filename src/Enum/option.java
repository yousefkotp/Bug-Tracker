public enum option {
    Admin,ProjectManager,Developer;
    private option(){}
    public String value(){
        return name();
    }
    public static option fromvalue(String v){
        return valueOf(v);
    }
}
