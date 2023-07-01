package Program.func.info;

public enum MethodOC {
    FRIED,
    BOILED,
    FROZEN,
    BAKED,
    STEWED;

    public static MethodOC addMethod(String method) {
        switch (method.toLowerCase()) {
            case "fry":
                return FRIED;
            case "boil":
                return BOILED;
            case "freeze":
                return FROZEN;
            case "bake":
                return BAKED;
            case "stew":
                return STEWED;
            default:
                System.out.println("-");
                return null;
        }
    }
}