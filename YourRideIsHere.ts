import { createWriteStream } from 'fs'
type ActionType = 'INPUT_1' | 'INPUT_2'
type ResultType = 'GO' | 'STAY' | 'NA'

interface ResultPairs {
    input1: string | undefined;
    input2: string | undefined;
    result: ResultType
}

class YourRideIsHere {
    #result: ResultPairs[] = []
    #action_tracker: ResultPairs & { action: ActionType } = {
        action: 'INPUT_1',
        input1: undefined,
        input2: undefined,
        result: 'NA'
    }

    #resetActionTracker() {
        this.#action_tracker = {
            action: 'INPUT_1',
            input1: undefined,
            input2: undefined,
            result: 'NA'
        }
    }

    #getCharPosition(char: string) {
        const charAscii = char.toUpperCase().charCodeAt(0);
        if (charAscii > 64 && charAscii < 91) return charAscii - 64;
    }

    #getResult(x: string, y: string): ResultType {
        let xProducts = 1;
        let yProducts = 1;

        for (let i = 0; i < x.length; i++) {
            xProducts *= this.#getCharPosition(x[i]);
        }

        for (let j = 0; j < y.length; j++) {
            yProducts *= this.#getCharPosition(y[j]);
        }


        if (xProducts % 47 === 27 && yProducts % 47 === 27) {
            return 'GO'
        }

        if (xProducts % 47 !== 27 && yProducts % 47 !== 27) {
            return 'STAY'
        }

        return 'NA'
    }

    calculate(input: string) {
        if (this.#action_tracker.action === 'INPUT_1') {
            this.#action_tracker.input1 = input;
            this.#action_tracker.action = 'INPUT_2'
            return
        }

        if (this.#action_tracker.action === 'INPUT_2') {
            this.#action_tracker.input2 = input;
            this.#action_tracker.result = this.#getResult(this.#action_tracker.input1, this.#action_tracker.input2)
        }

        this.#result.push(this.#action_tracker)
        this.#resetActionTracker();
    }

    getResult() {
        const w_stream = createWriteStream('output.out')
        for (let i = 0; i < this.#result.length; i++) {
            const { input1, input2, result } = this.#result[i]
            const out = `[${input1}, ${input2}] : ${result}`
            console.log(out)
            w_stream.write(result + '\n')
        }

        if (this.#action_tracker.input2 === undefined) {
            const out = `[${this.#action_tracker.input1}, ''] : Incomplete input`
            console.log(out)
            w_stream.write('Incomplete input\n')
            this.#resetActionTracker()
        }

        this.#result = []
        w_stream.end()
        w_stream.close()
    }
}

export default YourRideIsHere;