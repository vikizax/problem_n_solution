import { writeFile } from 'fs/promises'

type ActionType = 'giver' | 'gift' | 'giving'

interface IGiftGiverAction {
    name: string;
    amount: number;
    splitCount: number;
    action: ActionType
    actionCounter: number;
}

class GiftGivers {
    #inputs: string[] = [];
    #participantCount: number = 0;
    #participantToVal: { [key: string]: number } = {};

    constructor(inputs: string[]) {
        this.#inputs = inputs;
    }

    get commandInputs() {
        return this.#inputs
    }

    calculate() {
        try {
            const giftGiversAction: IGiftGiverAction = {
                name: '',
                action: 'giver',
                amount: 0,
                splitCount: 0,
                actionCounter: 0
            }

            const giftToProcessArr: string[] = this.#inputs.slice(6);

            for (let i = 0; i < giftToProcessArr.length; ++i) {

                const value = giftToProcessArr[i]

                if (giftGiversAction.action === 'giver') {
                    if (this.#participantToVal[value] === undefined) throw "Paticipant name input does not exists"
                    if (isNaN(Number(value)) === false) throw "Expected Name input but got number"
                    giftGiversAction.name = value
                    giftGiversAction.action = 'gift'
                    continue;
                }

                if (giftGiversAction.action === 'gift') {
                    const actions = value.split(' ');
                    if (actions.length < 2) throw "Expected amount and split count but found only 1 input."

                    const amount = Number(actions[0]);
                    const splitCount = Math.floor(Number(actions[1]))

                    if (isNaN(amount)) throw "Invalid amount"
                    if (isNaN(splitCount)) throw "Invalid split count"

                    giftGiversAction.amount = amount;
                    giftGiversAction.splitCount = splitCount;
                    giftGiversAction.actionCounter = splitCount;

                    const remainder = splitCount > 0 ? amount % splitCount : 0;
                    const currentGiftGiver = giftGiversAction.name;

                    this.#participantToVal[currentGiftGiver] -= amount;
                    this.#participantToVal[currentGiftGiver] += remainder;

                    giftGiversAction.action = 'giving';
                    continue;
                }

                if (giftGiversAction.action === 'giving') {
                    if (this.#participantToVal[value] === undefined) throw "Invalid participant name input"

                    if (giftGiversAction.actionCounter === 0) {
                        giftGiversAction.action = 'giver';
                        continue;
                    }

                    const amount = giftGiversAction.amount;
                    const splitCount = giftGiversAction.splitCount

                    const splitAmtPerPerson = splitCount > 0 ? Math.floor(amount / splitCount) : 0;
                    const currentRecipant = value;
                    this.#participantToVal[currentRecipant] += splitAmtPerPerson

                    giftGiversAction.actionCounter -= 1;

                    if (giftGiversAction.actionCounter === 0) {
                        giftGiversAction.action = 'giver';
                    }
                }
            }

        } catch (err) {
            console.error('CALCULATE_ERROR', err)
        }
    }

    getOutput() {
        Object.entries(this.#participantToVal).forEach(([name, value]) => {
            console.log(`${name} ${value}`)
        })
    }

    async getOutputToFile() {
        try {
            let fileContent: string = ''
            Object.entries(this.#participantToVal).forEach(([name, value]) => {
                fileContent += `${name} ${value}\n`
            })
            await writeFile('output.txt', fileContent)
        } catch (err) {
            console.error('FILE_OUTPUT_ERROR', err)
        }
    }


    init() {
        try {
            this.#participantCount = parseInt(this.#inputs[0]);
            for (let i = 1; i <= this.#participantCount; i++) {
                const participantName = this.#inputs[i]
                if (this.#participantToVal[participantName] !== undefined)
                    throw "Number of participants does not map to number of participants name"
                this.#participantToVal[participantName] = 0
            }
            if (Object.keys(this.#participantToVal).length !== this.#participantCount)
                throw "Number of participants does not map to number of participants name"

        } catch (err) {
            console.error("INVALID_INPUTS please check your input file.", err)
        }
    }
}

export default GiftGivers;