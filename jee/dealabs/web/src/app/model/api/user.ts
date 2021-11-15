export class User {

    private readonly _id: number;
    private readonly _pseudo: string;
    private readonly _firstName: string;
    private readonly _lastName: string;

    constructor(id: number, pseudo: string, firstName: string) {
        this._id = id;
        this._pseudo = pseudo;
        this._firstName = firstName;
    }

    get id(): number {
        return this._id;
    }

    get pseudo(): string {
        return this._pseudo;
    }

    get firstName(): string {
        return this._firstName;
    }

    get lastName(): string {
        return this._lastName;
    }

}
