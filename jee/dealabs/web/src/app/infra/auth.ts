export class Credentials {

    private readonly _name: string;
    private readonly _password: string;

    public static create(name: string, password: string): Credentials {
        return new Credentials(name, password);
    }

    private constructor(name: string, password: string) {
        this._name = name;
        this._password = password;
    }

    get name(): string {
        return this._name;
    }

    get password(): string {
        return this._password;
    }
}
