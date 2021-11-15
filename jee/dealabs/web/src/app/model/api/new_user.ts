export class NewUser {

    private pseudo: string;
    private firstName: string;
    private lastName: string;
    private password: string;

    public static from(data: any): NewUser {
        if (undefined === data) {
            throw new Error("Failed to create new user");
        }
        return new NewUser(
            data.pseudo,
            data.first_name,
            data.last_name,
            data.password
        );
    }

    private constructor(pseudo: string, firstName: string, lastName: string, password: string) {
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
