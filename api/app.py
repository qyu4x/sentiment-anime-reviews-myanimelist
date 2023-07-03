from flask import Flask, request, jsonify
import joblib
from sklearn.feature_extraction.text import TfidfVectorizer

app = Flask(__name__)

# load model
model = joblib.load('MALSentimentAnalyzerModel.joblib')
vectorizer = joblib.load('vectorizer.joblib')


@app.route('/api/mal/v1/predict-sentiment', methods=['POST'])
def predict_sentiment():
    try:
        data = request.get_json()

        if 'comment' not in data:
            return jsonify({'error': 'Input data must contain a comment'})

        comment = data['comment']

        data_vectorized = vectorizer.transform([comment])
        sentiment = model.predict(data_vectorized)[0]

        if sentiment == 0:
            sentiment_text = 'negative'
        elif sentiment == 1:
            sentiment_text = 'netral'
        else:
            sentiment_text = 'positive'

            return jsonify({'comment': comment, 'sentiment': sentiment_text}), 200

    except Exception as ex:
        return jsonify({'error': 'An error occurred in predicting sentiment', 'details': str(ex)}), 500


if __name__ == '__main__':
    app.run()
