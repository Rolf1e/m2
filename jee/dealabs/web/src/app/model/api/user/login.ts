export class Login {

    private nickname: string;
    private password: string;

    public static from(data: any): Login {
        if (undefined === data) {
            throw new Error("Failed to create new user");
        }
        return new Login(
            data.nickname,
            data.password
        );
    }

    private constructor(nickname: string, password: string) {
        this.nickname = nickname;
        this.password = password;
    }
}
