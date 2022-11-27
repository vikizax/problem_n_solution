import process from 'process';
import fs from 'fs';
import readline from 'readline';
import GiftGivers from './GiftGivers';

const inputFilePath = process.argv[2];

const lineReader = readline.createInterface({
    input: fs.createReadStream(inputFilePath)
})

const inputs: string[] = []
lineReader.on('line', line => inputs.push(line));
lineReader.on('close', () => {
    const giftGivers = new GiftGivers(inputs);
    giftGivers.init()
    giftGivers.calculate()
    giftGivers.getOutputToFile()
});