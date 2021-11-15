export class Objects {
    public static requireNonNull<T>(object: T | null): T {
        if (!object) {
            throw new Error("object is null")
        }
        return object;
    }
}
