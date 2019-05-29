using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraShake : MonoBehaviour
{
	private Vector3 startPos;
	public MonoBehaviour[] disableScripts;
	public float duration = 5;
	public float intensity = 1;
	public bool testRun;
    // Start is called before the first frame update
    void Awake()
    {
		MCP.cameraShake = this;
    }

    // Update is called once per frame
    void Update()
    {
		if (testRun)
		{
			testRun = false;
			StartCoroutine(ShakeCamera());
		}
    }
	public void Shake(float whatIntensity, float whatDuration)
	{
		intensity = whatIntensity;
		duration = whatDuration;
		StartCoroutine(ShakeCamera());
	}
	IEnumerator ShakeCamera()
	{
		startPos = transform.position;
		foreach(MonoBehaviour thing in disableScripts)
		{
			thing.enabled = false;
		}
		for(float i = duration; i > 0; i -= 0.02f)
		{
			transform.position = startPos + new Vector3(Random.Range(-intensity * 0.1f, intensity * 0.1f), Random.Range(-intensity * 0.1f, intensity * 0.1f), Random.Range(-intensity * 0.1f, intensity * 0.1f));
			yield return new WaitForSeconds(0.02f);
		}

		transform.position = startPos;
		foreach (MonoBehaviour thing in disableScripts)
		{
			thing.enabled = true;
		}
	}
}
