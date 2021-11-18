export class User {

    private readonly _id: number;
    private readonly _nickname: string;
    private readonly _firstName: string;
    private readonly _lastName: string;

    constructor(id: number, nickname: string, firstName: string) {
        this._id = id;
        this._nickname = nickname;
        this._firstName = firstName;
    }

    get id(): number {
        return this._id;
    }

    get nickname(): string {
        return this._nickname;
    }

    get firstName(): string {
        return this._firstName;
    }

    get lastName(): string {
        return this._lastName;
    }

}
