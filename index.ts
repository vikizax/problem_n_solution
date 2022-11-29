import process from 'process';
import fs from 'fs';
import readline from 'readline';
import YourRideIsHere from './YourRideIsHere';

const inputFilePath = process.argv[2];

const lineReader = readline.createInterface({
    input: fs.createReadStream(inputFilePath)
})

const yourRideIsHere = new YourRideIsHere();

lineReader.on('line', line => yourRideIsHere.calculate(line));
lineReader.on('close', () => {
    yourRideIsHere.getResult()
});