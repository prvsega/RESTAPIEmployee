package by.prvsega.restservice.util;

public enum RolesName {

    ADMIN("Admin"),
    MANAGER("Manager"),
    USER("User");
    private String nameEnum;

    RolesName(String nameEnum) {
        this.nameEnum = nameEnum;
    }

    public String getNameEnum() {
        return nameEnum;
    }
}
