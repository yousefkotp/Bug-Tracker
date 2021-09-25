public enum SignupEnum {
    Developer, ProjectManager;
    private SignupEnum(){}
    public String value(){
        return name();
    }
    public static SignupEnum fromvalue(String v){
        return valueOf(v);
    }
}
