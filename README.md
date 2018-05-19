# Tumblr URL Scanner

An application that checks the availability of Tumblr URLs.

## Usage

Running this creates a `list.txt` file where you can add whichever URLs you would like to scan for. 

* If a URL pops up, it will stay visible as long as it is available.
* The original time it was found will be displayed next to it.
* It will continuously search for any available URLs and loop once it finishes going through the list.
* If the list is edited, the scanner will automatically refresh.

## Notes

* Each URL should be on its own line.
* Special characters will be automatically removed.
* Letters with accent marks will be replaced with the same letter without the mark.
* Any "voided" URLs will appear on the scanner due to the way Tumblr handles them. Unfortunately, this cannot be avoided and it is best to remove that URL from the list.